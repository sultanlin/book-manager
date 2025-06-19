import { axiosInstance } from "@/lib/api-client"
import { Shelf } from "@/types/api"
import { queryOptions, useQuery } from "@tanstack/react-query"

export async function getShelves(): Promise<Shelf[]> {
  const response = await axiosInstance.get<Shelf[]>("/api/v1/shelves")
  return response.data
}

export const getShelvesQueryOptions = () => {
  return queryOptions({
    queryKey: ["shelves"],
    queryFn: () => getShelves(),
  })
}

export const useShelves = () => {
  return useQuery({
    ...getShelvesQueryOptions(),
  })
}
